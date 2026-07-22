// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: extension and member functions follow the same overload rules at runtime
 */

// TESTCASE NUMBER: 1
fun Int.foo(): Int = this + 1

class Bar {
    fun memberFoo(): String = "member"

    fun Int.extensionFoo(): String = "extension:$this"

    fun combined(): String = "${memberFoo()}-${3.extensionFoo()}"
}

fun box(): String {
    val topLevel = 2.foo()
    val inClass = Bar().combined()
    return if (topLevel == 3 && inClass == "member-extension:3") "OK" else "NOK top=$topLevel in=$inClass"
}
