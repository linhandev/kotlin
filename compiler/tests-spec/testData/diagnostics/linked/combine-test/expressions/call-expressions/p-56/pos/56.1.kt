// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 56 -> sentence 56
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 56 -> sentence 56
 *                declarations, declaration-site-variance-and-use-site-variance -> paragraph 56 -> sentence 56
 * NUMBER: 1
 * DESCRIPTION: contravariant in-projected type argument in function parameter accepts supertype argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Consumer<in T> {
    fun accept(x: T)
}

class C : Consumer<Number> {
    override fun accept(x: Number) {}
}

fun use(c: Consumer<Number>) {}

fun case_1() {
    use(C())
}
