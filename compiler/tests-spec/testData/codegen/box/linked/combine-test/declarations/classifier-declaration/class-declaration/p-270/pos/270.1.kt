// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 270 -> sentence 270
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 270 -> sentence 270
 * NUMBER: 1
 * DESCRIPTION: explicit public members of a class are accessible outside the class; covers body property, member function, and primary-constructor property; contrasts with p-260 default/implicit public class exposure and with p-269 internal module-only members
 */

// TESTCASE NUMBER: 1
class GateHolder {
    public val gate = 1
}

fun readGate(): Int = GateHolder().gate

// TESTCASE NUMBER: 2
class OpenBox {
    public fun open(): Int = 2
}

class OpenClient {
    fun call(): Int = OpenBox().open()
}

fun readOpen(): Int = OpenBox().open()

// TESTCASE NUMBER: 3
class TagBag(public val tag: String)

fun readTag(): String = TagBag("ok").tag

fun tagLength(): Int = TagBag("ok").tag.length

fun box(): String {
    if (readGate() != 1) return "NOK: gate"
    if (GateHolder().gate != 1) return "NOK: gate-direct"
    val holder: GateHolder = GateHolder()
    if (holder.gate != 1) return "NOK: via-holder"

    if (readOpen() != 2) return "NOK: open"
    if (OpenClient().call() != 2) return "NOK: open-client"
    val box: OpenBox = OpenBox()
    if (box.open() != 2) return "NOK: via-box"

    if (readTag() != "ok") return "NOK: tag"
    if (tagLength() != 2) return "NOK: tag-length"
    if (TagBag("ab").tag != "ab") return "NOK: tag-ab"
    val bag: TagBag = TagBag("ok")
    if (bag.tag != "ok") return "NOK: via-bag"
    return "OK"
}
