// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 357 -> sentence 357
 * declarations, declaration-visibility -> paragraph 357 -> sentence 357
 * declarations, property-declaration -> paragraph 357 -> sentence 357
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 357 -> sentence 357
 * NUMBER: 1
 * DESCRIPTION: 主构造 private var 生成私有可变属性
 */

// TESTCASE NUMBER: 1
class Acc(private var balance: Int) { fun add(x: Int) { balance += x }; fun get(): Int = balance }

// TESTCASE NUMBER: 1
fun test(): Int { val a = Acc(0); a.add(3); return a.get() }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
