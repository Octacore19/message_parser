package com.octacoresoftwares.message_parser

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** MessageParserPlugin */
class MessageParserPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private val parser: IParser by lazy {
    Parser()
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "octacore.message_parser/parser")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when (call.method) {
      "parseKimonoPurchaseRequest" -> {
        try {
          val terminalInfo = call.argument<HashMap<String, Any>>("terminalInfo")
          val transactionInfo = call.argument<HashMap<String, Any>>("transactionInfo")
          if (terminalInfo != null && transactionInfo != null) {
            val response = parser.parseKimonoPurchaseRequest(terminalInfo, transactionInfo)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseKimonoReversalRequest" -> {
        try {
          val terminalInfo = call.argument<HashMap<String, Any>>("terminalInfo")
          val transactionInfo = call.argument<HashMap<String, Any>>("transactionInfo")
          if (terminalInfo != null && transactionInfo != null) {
            val response = parser.parseKimonoReversalRequest(terminalInfo, transactionInfo)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSPurchaseRequest" -> {
        try {
          val terminalInfo = call.argument<HashMap<String, Any>>("terminalInfo")
          val transactionInfo = call.argument<HashMap<String, Any>>("transactionInfo")
          if (terminalInfo != null && transactionInfo != null) {
            val response = parser.parseNIBBSPurchaseRequest(terminalInfo, transactionInfo)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSReversalRequest" -> {
        try {
          val terminalInfo = call.argument<HashMap<String, Any>>("terminalInfo")
          val transactionInfo = call.argument<HashMap<String, Any>>("transactionInfo")
          if (terminalInfo != null && transactionInfo != null) {
            val response = parser.parseNIBBSReversalRequest(terminalInfo, transactionInfo)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSResponse" -> {
        try {
          val nResponse = call.argument<ByteArray>("nResponse")
          if (nResponse != null) {
            val response = parser.parseNIBBSResponse(nResponse)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSKeyRequest" -> {
        try {
          val terminalInfo = call.argument<HashMap<String, Any>>("terminalInfo")
          val code = call.argument<String>("code")
          if (terminalInfo != null && code != null) {
            val response = parser.parseNIBBSKeyRequest(terminalInfo, code)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSKeyResponse" -> {
        try {
          val nResponse = call.argument<ByteArray>("nResponse")
          val key = call.argument<String>("key")
          if (nResponse != null && key != null) {
            val response = parser.parseNIBBSKeyResponse(nResponse, key)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSTerminalParameterRequest" -> {
        try {
          val terminalInfo = call.argument<HashMap<String, Any>>("terminalInfo")
          if (terminalInfo != null) {
            val response = parser.parseNIBBSTerminalParameterRequest(terminalInfo)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      "parseNIBBSTerminalParameterResponse" -> {
        try {
          val nResponse = call.argument<ByteArray>("nResponse")
          if (nResponse != null) {
            val response = parser.parseNIBBSTerminalParameterResponse(nResponse)
            result.success(response)
          } else {
            result.error("1-0-0", "Argument(s) is missing", null)
          }
        } catch (e: Exception) {
          result.error("1-0-1", e.message, e.stackTrace.toString())
        }
      }
      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
