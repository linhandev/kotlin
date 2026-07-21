// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, constant-properties -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: const properties must be compile-time constants at allowed scopes and types
 */

// TESTCASE NUMBER: 1
const val nonConst = <!CONST_VAL_WITH_NON_CONST_INITIALIZER!>"".hashCode()<!>

// TESTCASE NUMBER: 2
class Holder {
    <!CONST_VAL_NOT_TOP_LEVEL_OR_OBJECT!>const<!> val member = 1
}

// TESTCASE NUMBER: 3
<!TYPE_CANT_BE_USED_FOR_CONST_VAL!>const<!> val list: List<Int> = listOf(1)
