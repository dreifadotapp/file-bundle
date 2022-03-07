package dreifa.app.fileBundle.builders

import dreifa.app.fileBundle.FileBundle
import dreifa.app.types.UniqueId
import java.io.File

class ScanDirectoryBuilder(
    private val textFileExtensions: Set<String> = defaultTextFileExtensions,
    private val knownTextFiles: Set<String> = defaultTextFileNames
) {

    private var id: UniqueId = UniqueId.alphanumeric()
    private var name: String = ""
    private var baseDirectory: String = "."
    private val items = ArrayList<dreifa.app.fileBundle.BundleItem>()

    fun withBaseDirectory(base: String): ScanDirectoryBuilder {
        this.baseDirectory = base
        return this
    }

    fun withId(id: UniqueId): ScanDirectoryBuilder {
        this.id = id
        return this
    }

    fun withName(name: String): ScanDirectoryBuilder {
        this.name = name
        return this
    }

    fun build(): FileBundle {
        val root = File(File(baseDirectory).canonicalPath)
        root.walk().forEach {
            if (it.isFile) {
                val path = it.canonicalPath.removePrefix(root.path + "/")

                if (isTextFile(path)) {
                    items.add(dreifa.app.fileBundle.TextBundleItem.fromFile(it, path, it.canExecute()))
                } else {
                    items.add(dreifa.app.fileBundle.BinaryBundleItem.fromFile(it, path, it.canExecute()))
                }
            }
        }

        return FileBundleBuilder()
            .withId(id)
            .withName(name)
            .addItems(items)
            .build()

    }

    private fun isTextFile(path: String): Boolean {
        return hasTextExtension(path) || isKnownTextFile(path)
    }


    private fun isKnownTextFile(path: String): Boolean {
        val parts = path.split("/")
        val fileName = parts[parts.size - 1]
        return knownTextFiles.contains(fileName)
    }

    private fun hasTextExtension(path: String): Boolean {
        return if (path.contains(".")) {
            val parts = path.split(".")
            val extension = parts[parts.size - 1].toLowerCase()
            textFileExtensions.contains(extension)
        } else {
            false
        }
    }

    companion object {
        val defaultTextFileExtensions = setOf("txt", "log", "md", "sh")
        val defaultTextFileNames = setOf("LICENCE", "LICENSE")
    }
}