package dreifa.app.fileBundle.builders

import dreifa.app.fileBundle.BundleItemList
import dreifa.app.fileBundle.FileBundle
import dreifa.app.types.UniqueId


class FileBundleBuilder {
    private var id: UniqueId = UniqueId.alphanumeric()
    private var name: String = ""
    private val items = ArrayList<dreifa.app.fileBundle.BundleItem>()
    fun withId(id: UniqueId): FileBundleBuilder {
        this.id = id
        return this
    }

    fun withName(name: String): FileBundleBuilder {
        this.name = name
        return this
    }

    fun addItem(item: dreifa.app.fileBundle.BundleItem): FileBundleBuilder {
        items.add(item)
        return this
    }

    fun addItems(items: Iterable<dreifa.app.fileBundle.BundleItem>): FileBundleBuilder {
        items.forEach {
            addItem(it)
        }
        return this
    }

    fun build(): FileBundle {
        return FileBundle(id, name, BundleItemList(items))
    }
}