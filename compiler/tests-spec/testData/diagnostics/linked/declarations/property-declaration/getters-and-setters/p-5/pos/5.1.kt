// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: property read and write access invoke getter and setter
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

fun observeReadsAndWrites(item: Intercepted) {
    val first = item.value
    item.value = first + 1
}

// TESTCASE NUMBER: 2
class InterceptedHolder {
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
