// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 2
 * DESCRIPTION: nullable array cannot be spread into a vararg parameter
 */

// TESTCASE NUMBER: 1
fun join(vararg parts: String): String = parts.joinToString()

val nullableParts: Array<String>? = null

fun spreadNullableArray() {
    join(<!SPREAD_OF_NULLABLE!>*<!><!ARGUMENT_TYPE_MISMATCH!>nullableParts<!>)
}
