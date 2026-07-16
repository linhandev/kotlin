// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: custom getter return type and custom setter parameter type must match declared property type
 */

// TESTCASE NUMBER: 1
class Holder {
    val x: Int
        get() = <!TYPE_MISMATCH!>"wrong"<!>
}

// TESTCASE NUMBER: 2
val y: Int
    get(): Int = <!TYPE_MISMATCH!>"wrong"<!>
