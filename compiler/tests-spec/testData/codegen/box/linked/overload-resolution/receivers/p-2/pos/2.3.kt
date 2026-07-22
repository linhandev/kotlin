// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: phantom static implicit this has higher priority than companion object receiver
 */

var flag1102 = false

enum class Case1102Enum {
    A1, A2;

    companion object values {
        operator fun invoke() {
            flag1102 = true
        }
    }

    fun foo() {
        values()
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    Case1102Enum.A1.foo()
    return if (!flag1102) "OK" else "NOK"
}
