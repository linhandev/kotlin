// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: typeArguments explicit type args
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p105.pos1

fun <T> pick(v: T): T = v

fun <A, B> pair(first: A, second: B): Pair<A, B> = first to second

fun box(): String {
    val p = pair<String, Int>("x", 1)
    return if (p.first == "x" && p.second == 1) "OK" else "NOK"
}
