// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, extension-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: extension property access uses receiver at runtime
 */

// TESTCASE NUMBER: 1
val Int.foo: Int
    get() = this + 1

fun box(): String {
    val result = 2.foo.foo
    return if (result == 4) "OK" else "NOK result=$result"
}
