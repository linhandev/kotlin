// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: Delegates.notNull cannot be used with nullable property type
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    var x: String? <!DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE!>by<!> Delegates.notNull()
}

fun case_1() = Box()
