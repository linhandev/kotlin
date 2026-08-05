// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 377 -> sentence 377
 * declarations, declaration-visibility -> paragraph 377 -> sentence 377
 * declarations, property-declaration -> paragraph 377 -> sentence 377
 * declarations, classifier-declaration, companion-object -> paragraph 377 -> sentence 377
 * NUMBER: 1
 * DESCRIPTION: private val 与 const val 在伴生对象中类外访问规则不同
 */

// TESTCASE NUMBER: 1
class C { companion object { private const val K = 1; fun internalGet(): Int = K } }

// TESTCASE NUMBER: 1
fun test(): Int = C.internalGet()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
