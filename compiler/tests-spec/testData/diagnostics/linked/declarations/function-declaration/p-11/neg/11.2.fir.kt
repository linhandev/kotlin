// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 2
 * DESCRIPTION: positional argument after named argument when vararg is not last
 */

// TESTCASE NUMBER: 1
fun afterVararg(vararg items: Int, required: String) {}

fun positionalAfterNamedVararg() {
    afterVararg(required = "x", <!MIXING_NAMED_AND_POSITIONAL_ARGUMENTS!>1<!>)
}
