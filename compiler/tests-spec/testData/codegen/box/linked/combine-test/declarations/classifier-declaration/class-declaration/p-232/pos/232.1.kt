// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 232 -> sentence 232
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 232 -> sentence 232
 *                inheritance, inheriting -> paragraph 232 -> sentence 232
 * NUMBER: 1
 * DESCRIPTION: class declaration type arguments on a consumer generic interface must match accept parameter types; covers concrete sinks and a generic RecordingSink<T> forwarding T; contrasts with p-151 minimal IntSink, p-231 producer Box, and next-point mismatched accept signature
 */

// TESTCASE NUMBER: 1
interface Sink<T> {
    fun accept(x: T)
}

class IntSink : Sink<Int> {
    var last: Int = 0
    override fun accept(x: Int) {
        last = x
    }
}

// TESTCASE NUMBER: 2
class StringSink : Sink<String> {
    var last: String = ""
    override fun accept(x: String) {
        last = x
    }
}

// TESTCASE NUMBER: 3
class RecordingSink<T>(initial: T) : Sink<T> {
    var last: T = initial
    override fun accept(x: T) {
        last = x
    }
}

fun box(): String {
    val ints = IntSink()
    ints.accept(1)
    if (ints.last != 1) return "NOK: int-sink"
    ints.accept(9)
    if (ints.last != 9) return "NOK: int-sink-overwrite"
    val asIntSink: Sink<Int> = ints
    asIntSink.accept(3)
    if (ints.last != 3) return "NOK: via-int-sink"

    val strings = StringSink()
    strings.accept("hi")
    if (strings.last != "hi") return "NOK: string-sink"
    val asStringSink: Sink<String> = strings
    asStringSink.accept("ok")
    if (strings.last != "ok") return "NOK: via-string-sink"

    val rec = RecordingSink(0L)
    rec.accept(10L)
    if (rec.last != 10L) return "NOK: recording-long"
    val asRec: Sink<Long> = rec
    asRec.accept(20L)
    if (rec.last != 20L) return "NOK: via-recording"
    val recBool = RecordingSink(false)
    recBool.accept(true)
    if (!recBool.last) return "NOK: recording-bool"
    return "OK"
}
