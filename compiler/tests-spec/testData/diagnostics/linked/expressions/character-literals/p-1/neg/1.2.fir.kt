// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: multi-character literals 'ab' and '  ' report TOO_MANY_CHARACTERS_IN_CHARACTER_LITERAL; '\' with lone backslash reports ILLEGAL_ESCAPE
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c1 = <!TOO_MANY_CHARACTERS_IN_CHARACTER_LITERAL!>'ab'<!>
}

// TESTCASE NUMBER: 2
fun case2() {
    val c2 = <!TOO_MANY_CHARACTERS_IN_CHARACTER_LITERAL!>'  '<!>
}

// TESTCASE NUMBER: 3
fun case3() {
    val c3 = <!ILLEGAL_ESCAPE!>'\'<!>
}
