// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 238 -> sentence 238
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 238 -> sentence 238
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 238 -> sentence 238
 *                inheritance, inheriting -> paragraph 238 -> sentence 238
 * NUMBER: 1
 * DESCRIPTION: an in type parameter cannot be passed to an invariant parent interface that produces it; this inheritance interaction differs from the direct class-member conflicts in the single-feature variance tests
 */

// TESTCASE NUMBER: 1
interface Producer<T> {
    fun produce(): T
}

interface BadConsumer<in T> : Producer<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>

class IntProducer : Producer<Int> {
    override fun produce(): Int = 1
}

// TESTCASE NUMBER: 2
interface ReadableSlot<T> {
    val value: T
}

interface BadSink<in T> : ReadableSlot<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>

class StringSlot : ReadableSlot<String> {
    override val value: String = "ok"
}

// TESTCASE NUMBER: 3
interface Supplier<T> {
    fun get(): T
    fun set(value: T)
}

interface BadHandler<in T> : Supplier<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>

class IntSupplier : Supplier<Int> {
    private var v = 0
    override fun get(): Int = v
    override fun set(value: Int) { v = value }
}
