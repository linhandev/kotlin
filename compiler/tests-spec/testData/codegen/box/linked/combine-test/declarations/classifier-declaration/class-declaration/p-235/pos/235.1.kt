// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 235 -> sentence 235
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 235 -> sentence 235
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 235 -> sentence 235
 *                inheritance, inheriting -> paragraph 235 -> sentence 235
 * NUMBER: 1
 * DESCRIPTION: classes and anonymous objects implementing covariant producer interfaces can be widened on the read side while preserving runtime values; contrasts with p-234 invariant interfaces and p-231 fixed generic producers
 */

// TESTCASE NUMBER: 1
interface Producer<out T> {
    fun produce(): T
}

class IntProducer(private val value: Int) : Producer<Int> {
    override fun produce(): Int = value
}

// TESTCASE NUMBER: 2
interface Source<out T> {
    val current: T
}

class StringSource(override val current: String) : Source<String>

// TESTCASE NUMBER: 3
interface Factory<out T> {
    fun create(): T
}

fun box(): String {
    val numberProducer: Producer<Number> = IntProducer(7)
    if (numberProducer.produce() != 7) return "NOK: class-producer"

    val anySource: Source<Any> = StringSource("value")
    if (anySource.current != "value") return "NOK: property-source"

    val charSequenceFactory: Factory<CharSequence> = object : Factory<String> {
        override fun create(): String = "anonymous"
    }
    if (charSequenceFactory.create() != "anonymous") return "NOK: anonymous-factory"
    return "OK"
}
