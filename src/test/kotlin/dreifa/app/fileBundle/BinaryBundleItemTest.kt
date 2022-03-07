package dreifa.app.fileBundle


class BinaryBundleItemTest : BaseBundleItemTest() {
    override fun buildBundleItem(path: String, isExecutable: Boolean): dreifa.app.fileBundle.BundleItem {
        return dreifa.app.fileBundle.BinaryBundleItem(path, "foo".toByteArray(), isExecutable)
    }
}