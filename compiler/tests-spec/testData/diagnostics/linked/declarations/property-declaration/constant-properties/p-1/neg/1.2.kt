// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, constant-properties -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: const val with enum type is forbidden
 */

// TESTCASE NUMBER: 1
enum class MyEnum { A }

<!TYPE_CANT_BE_USED_FOR_CONST_VAL!>const<!> val enumConst: MyEnum = MyEnum.A
