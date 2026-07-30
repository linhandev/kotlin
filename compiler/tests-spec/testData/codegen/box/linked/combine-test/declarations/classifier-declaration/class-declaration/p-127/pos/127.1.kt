// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 127 -> sentence 127
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 127 -> sentence 127
 * NUMBER: 1
 * DESCRIPTION: init block may call instance methods and observe their results in class declaration
 */

// TESTCASE NUMBER: 1
class Svc {
    var observed = -1

    fun ping(): Int = 1

    init {
        observed = ping()
    }
}

class Tag {
    var label = ""

    fun mark(): String = "ready"

    init {
        label = mark()
    }
}

class Accum {
    var sum = 0

    fun step(): Int = 10

    init {
        sum = step() + step()
    }
}

fun viaSvc(): Int = Svc().observed

fun viaTag(): String = Tag().label

fun viaAccum(): Int = Accum().sum

fun box(): String {
    if (viaSvc() != 1) return "NOK: svc"
    if (viaTag() != "ready") return "NOK: tag"
    if (viaAccum() != 20) return "NOK: accum"
    return "OK"
}
