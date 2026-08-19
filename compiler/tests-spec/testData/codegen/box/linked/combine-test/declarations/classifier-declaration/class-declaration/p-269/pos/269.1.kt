// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 269 -> sentence 269
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 269 -> sentence 269
 * NUMBER: 1
 * DESCRIPTION: internal members of a public class are accessible outside the class in the same module; covers body property, member function, and primary-constructor property; contrasts with p-262 internal class focus, p-267/p-268 protected hierarchy rules, and declaration-visibility p-4 baseline
 */

// TESTCASE NUMBER: 1
class CodeHolder {
    internal val code = 42
}

fun readCode(): Int = CodeHolder().code

// TESTCASE NUMBER: 2
class SignalBox {
    internal fun signal(): Int = 7
}

class SignalClient {
    fun ping(): Int = SignalBox().signal()
}

fun readSignal(): Int = SignalBox().signal()

// TESTCASE NUMBER: 3
class LabelBag(internal val label: String)

fun readLabel(): String = LabelBag("ok").label

fun labelLength(): Int = LabelBag("ok").label.length

fun box(): String {
    if (readCode() != 42) return "NOK: code"
    if (CodeHolder().code != 42) return "NOK: code-direct"
    val holder: CodeHolder = CodeHolder()
    if (holder.code != 42) return "NOK: via-holder"

    if (readSignal() != 7) return "NOK: signal"
    if (SignalClient().ping() != 7) return "NOK: signal-client"
    val box: SignalBox = SignalBox()
    if (box.signal() != 7) return "NOK: via-box"

    if (readLabel() != "ok") return "NOK: label"
    if (labelLength() != 2) return "NOK: label-length"
    if (LabelBag("ab").label != "ab") return "NOK: label-ab"
    val bag: LabelBag = LabelBag("ok")
    if (bag.label != "ok") return "NOK: via-bag"
    return "OK"
}
