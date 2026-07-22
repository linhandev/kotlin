// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: property read and write access invoke getter and setter at runtime
 */

// TESTCASE NUMBER: 1
class Intercepted {
    private var storage = 0

    var value: Int
        get() {
            storage++
            return storage
        }
        set(v) {
            storage = v
        }
}

fun box(): String {
    val item = Intercepted()
    val first = item.value
    item.value = 10
    val second = item.value
    return if (first == 1 && second == 11) "OK" else "NOK first=$first second=$second"
}
