#import "Toast.h"
#import "Toast+UIView.h"
#import <Cordova/CDV.h>

#import "ApplicationManager.h" // Steroids build

@implementation Toast

- (void)show:(CDVInvokedUrlCommand*)command {

  NSString *message  = [command.arguments objectAtIndex:0];
  NSString *duration = [command.arguments objectAtIndex:1];
  NSString *position = [command.arguments objectAtIndex:2];
  NSString *image = [command.arguments objectAtIndex:3];

  if (![position isEqual: @"top"] && ![position isEqual: @"center"] && ![position isEqual: @"bottom"]) {
    CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"invalid position. valid options are 'top', 'center' and 'bottom'"];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    return;
  }

  NSInteger durationInt;
  if ([duration isEqual: @"short"]) {
    durationInt = 4;
  } else if ([duration isEqual: @"long"]) {
    durationInt = 7;
  } else {
    CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"invalid duration. valid options are 'short' and 'long'"];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    return;
  }

  ApplicationViewController *root = (ApplicationViewController*)[[ApplicationManager instance] currentRootViewController];
  //[root.webView makeToast:message duration:durationInt position:position image:image];
  for (WebViewController *controller in [root.allWebViewControllers allValues]) {
    if ([controller.webView isVisible]) {
      [controller.webView makeToast:message duration:durationInt position:position image:image];
    }
  }
    
  //[self.webView makeToast:message duration:durationInt position:position image:image];

  CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
  [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
