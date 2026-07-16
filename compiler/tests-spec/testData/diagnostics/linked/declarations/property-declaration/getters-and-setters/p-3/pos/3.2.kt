// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: default accessor with changed visibility only
 */

// TESTCASE NUMBER: 1
class Holder {
    var data: Int = 0
        private set
}
