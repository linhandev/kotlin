// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 353 -> sentence 353
 * declarations, declaration-visibility -> paragraph 353 -> sentence 353
 * declarations, property-declaration -> paragraph 353 -> sentence 353
 * NUMBER: 1
 * DESCRIPTION: 类体 private var 可在类内修改
 */

// TESTCASE NUMBER: 1
class C { private var n = 0; fun inc(): Int { n += 1; return n } }

// TESTCASE NUMBER: 1
fun test(): Int = C().inc()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
