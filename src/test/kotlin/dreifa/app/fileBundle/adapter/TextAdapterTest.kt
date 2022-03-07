package dreifa.app.fileBundle.adapter

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import dreifa.app.fileBundle.Fixtures
import dreifa.app.fileBundle.adapters.FileBundleAdapter
import dreifa.app.fileBundle.adapters.TextAdapter
import dreifa.app.types.UniqueId
import org.junit.jupiter.api.Test
import java.io.File

class TextAdapterTest : BaseAdapterTest<String>() {

    private val root = "src/test/resources/dreifa/app/fileBundle/adapter/TextAdapterTest"

    override val singleTextFile by lazy {
        loadAdapted("singleTextFile.txt")
    }

    override val mixOfLineTermination by lazy {
        loadAdapted("mixOfLineTermination.txt")
    }

    override val singleBinaryFile by lazy {
        loadAdapted("singleBinaryFile.txt")
    }

    override val exampleFiles by lazy {
        loadAdapted("exampleFiles.txt")
    }


    override fun createAdapter(mode: DataMode): FileBundleAdapter<String> = TextAdapter()


    override fun loadAdapted(path: String): String {
        return File("$root/$path").readText()
    }

    override fun storeAdapted(path: String, adapted: String) {
        File("$root/$path").writeText(adapted)
    }

    override fun assertAdapted(actual: String, expected: String) {
        assertThat(actual, equalTo(expected))
    }






}


