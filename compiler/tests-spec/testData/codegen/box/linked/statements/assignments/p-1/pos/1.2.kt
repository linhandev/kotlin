// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: h.value = 10 invokes custom setter and stored == 10 at runtime
 */

class Holder {
    var stored = 0
    var value: Int
        get() = stored
        set(v) { stored = v }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val h = Holder()
    h.value = 10
    return if (h.stored == 10) "OK" else "NOK"
}
