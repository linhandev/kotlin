// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 375 -> sentence 375
 * declarations, declaration-visibility -> paragraph 375 -> sentence 375
 * declarations, property-declaration -> paragraph 375 -> sentence 375
 * NUMBER: 1
 * DESCRIPTION: lateinit var 可为 private 且仅类内使用
 */

// TESTCASE NUMBER: 1
class C { private lateinit var name: String; fun set(n: String) { name = n }; fun get(): String = name }

// TESTCASE NUMBER: 1
fun test(): String { val c = C(); c.set("Ann"); return c.get() }

fun box(): String {
    if (test() != "Ann") return "NOK"
    return "OK"
}
