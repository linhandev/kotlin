// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 20 -> sentence 20
 *                syntax-and-grammar, syntax-grammar -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: illegal import syntax with call parentheses reports a syntax error
 */
// TESTCASE NUMBER: 1
import kotlin.collections.listOf<!SYNTAX!>(<!><!SYNTAX!>1<!><!SYNTAX!>)<!>

fun case_1() {}
