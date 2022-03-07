package dreifa.app.fileBundle

import dreifa.app.fileBundle.builders.FileBundleBuilder
import dreifa.app.types.UniqueId

object Fixtures {

    fun helloWorldBundle(id: UniqueId = UniqueId.randomUUID()): FileBundle {
        return FileBundleBuilder()
            .withName("HelloWorldBundle")
            .withId(id)
            .addItem(dreifa.app.fileBundle.TextBundleItem("greeting.txt", "Hello, world"))
            .build()
    }

    fun mixOfLineTerminationBundle(id: UniqueId = UniqueId.randomUUID()): FileBundle {
        return FileBundleBuilder()
            .withName("MixOfLineTerminationBundle")
            .withId(id)
            .addItem(dreifa.app.fileBundle.TextBundleItem("unix.txt", "Hello,\n world"))
            .addItem(dreifa.app.fileBundle.TextBundleItem("windows.txt", "Hello,\r\n world"))
            .addItem(dreifa.app.fileBundle.TextBundleItem("trailing-unix.txt", "Hello, world\n"))
            .addItem(dreifa.app.fileBundle.TextBundleItem("trailing-windows.txt", "Hello, world\r\n"))
            .build()
    }

    fun binaryBundle(id: UniqueId = UniqueId.randomUUID()): FileBundle {
        return FileBundleBuilder()
            .withName("BinaryBundle")
            .withId(id)
            .addItem(dreifa.app.fileBundle.BinaryBundleItem("greeting.bin", "Hello, world".toByteArray(), true))
            .build()
    }


    fun allExampleFiles(id: UniqueId = UniqueId.randomUUID()): FileBundle {
        return FileBundleBuilder()
            .withName("ExampleFiles")
            .withId(id)
            .addItem(dreifa.app.fileBundle.TextBundleItem.fromResource("/examples/mobydick.txt", "mobydick.txt"))
            .addItem(dreifa.app.fileBundle.BinaryBundleItem.fromResource("/examples/r3.svg", "r3.svg"))
            .addItem(dreifa.app.fileBundle.TextBundleItem("this/is/along/path.txt", "foo"))
            .addItem(dreifa.app.fileBundle.TextBundleItem("trailing-new-line.txt", "foo\n"))
            .addItem(dreifa.app.fileBundle.TextBundleItem.fromResource("/examples/verylongline.txt", "verylongline.txt"))
            .addItem(dreifa.app.fileBundle.TextBundleItem.fromResource("/examples/LICENCE", "LICENCE"))
            .addItem(dreifa.app.fileBundle.TextBundleItem.fromResource("/examples/foo.sh", "foo.sh", true))
            .build()
    }
}