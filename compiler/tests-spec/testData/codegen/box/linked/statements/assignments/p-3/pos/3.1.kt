// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Safe assignment with null receiver does not invoke property setter
 */

var setterCalls = 0

class Holder {
    var n = 0
    var value: Int
        get() = n
        set(v) {
            setterCalls++
            n = v
        }
}

// TESTCASE NUMBER: 1
fun box(): String {
    setterCalls = 0
    val h: Holder? = null
    h?.value = 99
    return if (setterCalls == 0) "OK" else "NOK"
}
