// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 247 -> sentence 247
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 247 -> sentence 247
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 247 -> sentence 247
 *                inheritance, inheriting -> paragraph 247 -> sentence 247
 * NUMBER: 1
 * DESCRIPTION: precise types for star-projected interface producer reads as Any?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

class IntBox : Box<Int> {
    override fun get(): Int = 7
}

fun case1(b: Box<*>) {
    val x = b.get()
    x checkType { check<Any?>() }
    checkSubtype<Any?>(x)
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    val current: T
}

class StringHolder(override val current: String) : Holder<String>

fun case2(h: Holder<*>) {
    h.current checkType { check<Any?>() }
}

// TESTCASE NUMBER: 3
interface Factory<T> {
    fun create(): T
}

fun case3(f: Factory<*>) {
    f.create() checkType { check<Any?>() }
}
