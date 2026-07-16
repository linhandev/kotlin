// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -EXTENSION_SHADOWED_BY_MEMBER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 2
 * DESCRIPTION: duplicate extension declarations on the same receiver conflict
 */

// TESTCASE NUMBER: 1
<!CONFLICTING_OVERLOADS!>fun String.describe(): String<!> = "one"

<!CONFLICTING_OVERLOADS!>fun String.describe(): String<!> = "two"
