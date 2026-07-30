// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 236 -> sentence 236
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 236 -> sentence 236
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 236 -> sentence 236
 *                inheritance, inheriting -> paragraph 236 -> sentence 236
 * NUMBER: 1
 * DESCRIPTION: an out type parameter cannot be passed to an invariant parent interface that consumes it; this inheritance interaction differs from the direct class-member conflicts in p-10 and p-136
 */

// TESTCASE NUMBER: 1
interface Sink<T> {
    fun accept(value: T)
}

interface BadProducer<out T> : Sink<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>

class IntSink : Sink<Int> {
    override fun accept(value: Int) {}
}

// TESTCASE NUMBER: 2
interface MutableSlot<T> {
    var value: T
}

interface BadSource<out T> : MutableSlot<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>

class StringSlot : MutableSlot<String> {
    override var value: String = ""
}

// TESTCASE NUMBER: 3
interface Transformer<T> {
    fun transform(value: T): T
}

interface BadFactory<out T> : Transformer<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>

class IntTransformer : Transformer<Int> {
    override fun transform(value: Int): Int = value
}
