package dreifa.app.fileBundle

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test


class TextBundleItemTest : BaseBundleItemTest() {
    override fun buildBundleItem(path: String, isExecutable: Boolean): dreifa.app.fileBundle.BundleItem {
        return dreifa.app.fileBundle.TextBundleItem(path, "foo", isExecutable)
    }

    @Test
    fun `should replace windows style line termination`() {
        val rhyme = "\tMary\nhad\r\nlittle\nlamb.\r\n"
        val item = dreifa.app.fileBundle.TextBundleItem("rhyme.txt", rhyme)
        assertThat(item.content, equalTo("\tMary\nhad\nlittle\nlamb.\n"))
    }
}