// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 375 -> sentence 375
 * declarations, declaration-visibility -> paragraph 375 -> sentence 375
 * declarations, property-declaration -> paragraph 375 -> sentence 375
 * NUMBER: 1
 * DESCRIPTION: lateinit var can be private and used only inside class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private lateinit var name: String; fun set(n: String) { name = n }; fun get(): String = name }

// TESTCASE NUMBER: 1
fun test(): String { val c = C(); c.set("Ann"); return c.get() }

fun case1() {
    checkSubtype<String>(test())
}
