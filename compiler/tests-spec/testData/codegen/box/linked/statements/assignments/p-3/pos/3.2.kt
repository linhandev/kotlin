// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Safe assignment with non-null receiver invokes property setter
 */

class Holder {
    var n = 0
    var setterCalls = 0
    var value: Int
        get() = n
        set(v) {
            setterCalls++
            n = v
        }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val h: Holder? = Holder()
    h?.value = 10
    return if (h!!.n == 10 && h.setterCalls == 1) "OK" else "NOK"
}
