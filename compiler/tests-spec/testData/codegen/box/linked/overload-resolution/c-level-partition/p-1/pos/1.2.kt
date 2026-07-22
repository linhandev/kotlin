/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, c-level-partition -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: member function-like callable wins over member property-like callable with same name
 */

class Case1124 {
    fun go1124(): String = "fn"

    val go1124 = object {
        operator fun invoke(): String = "prop"
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Case1124().go1124()
    return if (result == "fn") "OK" else "NOK: $result"
}
