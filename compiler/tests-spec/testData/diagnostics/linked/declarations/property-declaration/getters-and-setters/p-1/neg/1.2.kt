// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: val cannot have a custom setter
 */

// TESTCASE NUMBER: 1
class Holder {
    val x: Int = 0
        <!VAL_WITH_SETTER!>set(value) { }<!>
}
