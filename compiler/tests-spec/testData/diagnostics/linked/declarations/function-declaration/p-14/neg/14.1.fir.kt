// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: spread requires an array expression at a vararg parameter position
 */

// TESTCASE NUMBER: 1
fun foo(vararg i: Int): Int = i.sum()

fun spreadScalar() {
    foo(*<!ARGUMENT_TYPE_MISMATCH!>1<!>)
}

// TESTCASE NUMBER: 2
fun join(x: Int, vararg a: String): String = "$x${a.joinToString()}"

fun spreadOnNonVarargParameter() {
    join(<!NON_VARARG_SPREAD!>*<!>1, "2")
}
