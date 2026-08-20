// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 239 -> sentence 239
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 239 -> sentence 239
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 239 -> sentence 239
 * NUMBER: 1
 * DESCRIPTION: an out type parameter cannot appear in both out and in member positions of the same interface; differs from single-position conflicts in p-10/p-136 and from inheritance-mediated conflicts in p-236
 */

// TESTCASE NUMBER: 1
interface BadChannel<out T> {
    fun produce(): T
    fun consume(x: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>)
}

class OkProducer<out T>(private val v: T) {
    fun produce(): T = v
}

// TESTCASE NUMBER: 2
interface BadSource<out T> {
    val current: T
    fun accept(x: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>)
}

class OkSource<out T>(val current: T)

// TESTCASE NUMBER: 3
interface BadFactory<out T> {
    fun create(): T
    var slot: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>
}

class OkFactory<out T>(private val v: T) {
    fun create(): T = v
}
