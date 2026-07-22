// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: member extension inside a classifier is callable with dispatch receiver at runtime
 */

// TESTCASE NUMBER: 1
class Bar {
    fun memberLabel(): String = "bar"

    fun Int.extensionLabel(): String = "int:$this"

    fun useMember(): String = memberLabel()

    fun useExtension(): String = 2.extensionLabel()
}

fun box(): String {
    val bar = Bar()
    return if (bar.useMember() == "bar" && bar.useExtension() == "int:2") "OK" else "NOK"
}
