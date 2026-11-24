package com.dojo.tasks.ui.theme

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Locks down the window theme that Android draws before Compose's first frame.
 * Compose already follows the system dark setting (Theme.kt); this keeps the
 * activity window and the API 31+ splash from being fixed to a light theme.
 * Unit tests run with the `app/` module as the working directory.
 */
class ThemeResourcesTest {

    private val androidNs = "http://schemas.android.com/apk/res/android"

    private fun parse(path: String): Document {
        val file = File(path)
        assertNotNull("missing $path (working dir: ${File("").absolutePath})", file.takeIf { it.isFile })
        return DocumentBuilderFactory.newInstance()
            .apply { isNamespaceAware = true }
            .newDocumentBuilder()
            .parse(file)
    }

    private fun appThemeParent(path: String): String? {
        val styles = parse(path).getElementsByTagName("style")
        for (i in 0 until styles.length) {
            val style = styles.item(i) as Element
            if (style.getAttribute("name") == "Theme.DojoTasks") return style.getAttribute("parent")
        }
        return null
    }

    // HNG-1: a fixed platform Light theme paints a white window before Compose in dark mode.
    @Test
    fun `manifest uses the app day night theme`() {
        val application = parse("src/main/AndroidManifest.xml")
            .getElementsByTagName("application").item(0) as Element
        assertEquals("@style/Theme.DojoTasks", application.getAttributeNS(androidNs, "theme"))
    }

    // HNG-1: light mode keeps today's light window.
    @Test
    fun `day theme uses a light window`() {
        assertEquals(
            "android:Theme.Material.Light.NoActionBar",
            appThemeParent("src/main/res/values/themes.xml"),
        )
    }

    // HNG-1: dark mode gets a dark window, so there is no light flash on launch.
    @Test
    fun `night theme uses a dark window`() {
        val parent = appThemeParent("src/main/res/values-night/themes.xml")
        assertEquals("android:Theme.Material.NoActionBar", parent)
        assertFalse(parent!!.contains("Light"))
    }
}
