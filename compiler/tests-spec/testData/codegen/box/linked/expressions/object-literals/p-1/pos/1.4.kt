// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: private function may return anonymous object with multiple supertypes
 */

// TESTCASE NUMBER: 1

open class Base
interface I

class M {
    private fun qux() = object : Base(), I {}
    fun check(): Base = qux()
}

fun box(): String {
    val b = M().check()
    return if (b is Base) "OK" else "NOK"
}
