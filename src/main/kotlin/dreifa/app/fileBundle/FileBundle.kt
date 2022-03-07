package dreifa.app.fileBundle

import dreifa.app.fileBundle.adapters.TextAdapter
import dreifa.app.types.SimpleImmutableList
import dreifa.app.types.UniqueId

class BundleItemList(items: List<BundleItem>) : SimpleImmutableList<dreifa.app.fileBundle.BundleItem>(items) {
    override fun equals(other: Any?): Boolean {
        return if (other is BundleItemList) {
            // TODO - we are treating this is a set , not a list
            // should be clearer in the name and signature
            if (this.size == other.size) {
                val set1 = this.toSet()
                val set2 = other.toSet()
                set1 == set2
            } else {
                false
            }
        } else {
            false
        }
    }
}

data class FileBundle(
    val id: UniqueId,
    val name: String,   // should there be limits on the character set in name?
    val items: BundleItemList
) {

    override fun equals(other: Any?): Boolean {
        return if (other is FileBundle) {
            (id == other.id && name == other.name && items == other.items)
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return id.hashCode() xor name.hashCode()
    }

    override fun toString(): String {
        val adapter = TextAdapter(TextAdapter.Options(summaryMode = true))
        return adapter.fromBundle(this)
    }
}
