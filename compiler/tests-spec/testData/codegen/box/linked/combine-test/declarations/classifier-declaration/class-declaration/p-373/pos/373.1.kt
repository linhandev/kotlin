// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 373 -> sentence 373
 * declarations, declaration-visibility -> paragraph 373 -> sentence 373
 * declarations, property-declaration -> paragraph 373 -> sentence 373
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 373 -> sentence 373
 * NUMBER: 1
 * DESCRIPTION: secondary constructor can access primary constructor private val via this
 */

// TESTCASE NUMBER: 1
class User(private val id: Int) { constructor(tag: String): this(tag.hashCode()); fun get(): Int = id }

// TESTCASE NUMBER: 1
fun test(): Int = User("a").get()

fun box(): String {
    if (test() <= 0) return "NOK"
    return "OK"
}
