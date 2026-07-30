// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 243 -> sentence 243
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 243 -> sentence 243
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 243 -> sentence 243
 *                inheritance, inheriting -> paragraph 243 -> sentence 243
 * NUMBER: 1
 * DESCRIPTION: a subinterface may fix the covariant parent type argument, and implementations remain assignable to a widened parent producer type; contrasts with p-235 direct out assignment and p-242 invariant parent/child type-parameter reuse
 */

// TESTCASE NUMBER: 1
interface Source<out T> {
    fun next(): T
}

interface IntSource : Source<Int>

class FixedIntSource(private val value: Int) : IntSource {
    override fun next(): Int = value
}

// TESTCASE NUMBER: 2
interface Producer<out T> {
    val current: T
}

interface StringProducer : Producer<String>

class FixedStringProducer(override val current: String) : StringProducer

// TESTCASE NUMBER: 3
interface Factory<out T> {
    fun create(): T
}

interface BoolFactory : Factory<Boolean>

fun box(): String {
    val asNumber: Source<Number> = FixedIntSource(7)
    if (asNumber.next() != 7) return "NOK: int-source"
    val viaIntSource: IntSource = FixedIntSource(7)
    if (viaIntSource.next() != 7) return "NOK: via-int-source"

    val asAny: Producer<Any> = FixedStringProducer("value")
    if (asAny.current != "value") return "NOK: string-producer"
    val viaString: StringProducer = FixedStringProducer("value")
    if (viaString.current != "value") return "NOK: via-string-producer"

    val asAnyFactory: Factory<Any> = object : BoolFactory {
        override fun create(): Boolean = true
    }
    if (asAnyFactory.create() != true) return "NOK: bool-factory"
    return "OK"
}
