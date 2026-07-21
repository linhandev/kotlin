// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: custom getter reads backing field and custom setter writes backing field for member property
 */

// TESTCASE NUMBER: 1
var counter: Int = 0
    get() = field + 1
    set(value) { field = value * 2 }

// TESTCASE NUMBER: 2
class LazyHolder {
    private var backing = 0

    val computed: Int
        get() {
            backing++
            return backing
        }
}
