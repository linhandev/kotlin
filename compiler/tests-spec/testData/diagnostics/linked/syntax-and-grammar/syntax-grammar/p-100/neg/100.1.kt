// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: assignableSuffix navigation suffix trailing dot
 *
 * NOTE: See p-100/pos/100.1.kt — assignableSuffix typeArguments-only cannot be
 * constructed as a valid assign LHS in Kotlin; typeArguments on calls are covered
 * under postfixUnarySuffix in p-95/pos/95.2.kt. This neg case is for navigationSuffix.
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p100.neg1

class Holder {
    var value = 0
}

fun case1() {
    val h = Holder()
    h.<!SYNTAX!><!>
}
