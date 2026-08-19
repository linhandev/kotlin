// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 56 -> sentence 56
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 56 -> sentence 56
 *                declarations, declaration-site-variance-and-use-site-variance -> paragraph 56 -> sentence 56
 * NUMBER: 1
 * DESCRIPTION: contravariant in-projected type argument in function parameter accepts supertype argument
 */

// TESTCASE NUMBER: 1
interface Consumer<in T> {
    fun accept(x: T)
}

class C : Consumer<Number> {
    var last: Number? = null
    override fun accept(x: Number) {
        last = x
    }
}

fun use(c: Consumer<Number>) {
    c.accept(42)
}

fun box(): String {
    val c = C()
    use(c)
    if (c.last != 42) return "NOK"
    return "OK"
}
