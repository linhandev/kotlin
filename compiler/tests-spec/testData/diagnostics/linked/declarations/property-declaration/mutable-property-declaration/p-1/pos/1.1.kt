// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, mutable-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: mutable var assignment and custom setter
 */

var topLevelVar = 1

// TESTCASE NUMBER: 1
fun case1() {
    topLevelVar = 2
}

class MutableHolder {
    var value: Int = 0
        set(v) { field = v + 1 }

    fun mutate() {
        value = 10
    }
}
